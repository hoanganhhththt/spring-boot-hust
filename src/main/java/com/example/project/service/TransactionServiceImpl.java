package com.example.project.service;

import com.example.project.dao.TransactionDAO;
import com.example.project.dao.UserDAO;
import com.example.project.entity.Transaction;
import com.example.project.entity.User;
import com.example.project.request.GetFeeReq;
import com.example.project.request.ListTransactionReq;
import com.example.project.request.UpdateTransactionReq;
import com.example.project.response.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionDAO transactionDAO;
    private UserDAO userDAO;

    @Autowired
    public TransactionServiceImpl(TransactionDAO theTransactionDAO, UserDAO theUserDAO) {
        transactionDAO = theTransactionDAO;
        userDAO = theUserDAO;
    }
    @Override
    public List<Transaction> findAllTransaction() {
        return transactionDAO.findAll();
    }

    @Transactional
    @Override
    public Transaction save(Transaction theTransaction) {
        return transactionDAO.save(theTransaction);
    }

    @Override
    public Transaction findTransactionById(int theId) {
        Optional<Transaction> result = transactionDAO.findById(theId);
        Transaction theTransaction = null;
        if(result.isPresent()) {
            theTransaction = result.get();
        } else{
            throw new RuntimeException("Did not find transaction id -" + theId);
        }
        return theTransaction;
    }

    @Override
    public ApiResponse<?> findByUserBuyAndDateTimeBetween(ListTransactionReq request) {

        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<Transaction> response = transactionDAO.findTransactionByUserBuyAndDateTimeBetween(request.getUserBuy(),request.getFromDate(), request.getToDate(), pageable);;;
        if(request.getUserBuy() == 0) {
            response = transactionDAO.findTransactionByDateTimeBetween(request.getFromDate(), request.getToDate(), pageable);
        }
        if(response.getContent().isEmpty()) {
            ApiResponse<String> res = new ApiResponse<>("00", "", "Thành công");
            return res;
        } else {
            List<ListTransactionRes> result = new ArrayList<>();
            for(Transaction transaction : response) {
                ListTransactionRes tempRes = new ListTransactionRes(transaction.getId(),
                        transaction.getTitle(),
                        transaction.getDateTime(),
                        transaction.getAverageAmount(),
                        transaction.getFeeAmount(),
                        transaction.getNote(),
                        null);
                Optional<User> userBuy = userDAO.findById(transaction.getUserBuy());
                tempRes.setUserBuy(userBuy.get().getFullName());
                for(User tempUser: transaction.getUsers()) {
                    tempRes.add(tempUser.getFullName());
                }
                result.add(tempRes);
            }
            int totalRows = (int) response.getTotalElements();
            ListTransaction list = new ListTransaction(result, totalRows);
            System.out.println(list);
            ApiResponse<ListTransaction> res = new ApiResponse<>("00", list, "Thành công");
            return res;
        }
    }

    @Override
    public List<GetFeeRes> getTotalFeeByUser(GetFeeReq request) {
        List<Transaction> tempTransaction = transactionDAO.findTransactionByDateTimeBetween(request.getFromDate(), request.getToDate());
        Map<Integer, BigDecimal> totalFeeUseByUser = new HashMap<>();
        List<User> users = userDAO.findAll();

        for (User user : users) {
            totalFeeUseByUser.put(user.getId(), BigDecimal.ZERO);
        }

        for(Transaction transaction: tempTransaction) {
            for(User user: transaction.getUsers()) {
                totalFeeUseByUser.put(user.getId(), totalFeeUseByUser.getOrDefault(user.getId(), new BigDecimal(0)).add(transaction.getAverageAmount()));
            }
        }
        List<GetFeeRes> result = new ArrayList<>();
        for(Map.Entry<Integer, BigDecimal> entry: totalFeeUseByUser.entrySet()){
            Integer userId = entry.getKey();
            BigDecimal feeAmount = entry.getValue();
            Optional<User> user = userDAO.findById(userId);

            GetFeeRes feeRes = new GetFeeRes();
            feeRes.setId(user.get().getId());
            feeRes.setFullName(user.get().getFullName());
            feeRes.setFeeAmount(feeAmount);
            result.add(feeRes);
        }
        return result;
    }

    @Override
    public List<GetFeeRes> getToltalFeeByUserBuy(GetFeeReq request) {
        List<Transaction> transactions = transactionDAO.findTransactionByDateTimeBetween(request.getFromDate(), request.getToDate());
        List<User> users = userDAO.findAll();
        Map<Integer, BigDecimal> totalFeeByUserBuy = new HashMap<>();
        for (User user : users) {
            totalFeeByUserBuy.put(user.getId(), BigDecimal.ZERO);
        }
        for (Transaction transaction : transactions) {
            Integer userBuy = transaction.getUserBuy();
            BigDecimal fee = totalFeeByUserBuy.getOrDefault(userBuy, BigDecimal.ZERO);
            fee = fee.add(transaction.getFeeAmount());
            totalFeeByUserBuy.put(userBuy, fee);
        }
        List<GetFeeRes> result = new ArrayList<>();
        for(Map.Entry<Integer, BigDecimal> entry: totalFeeByUserBuy.entrySet() ) {
            Integer userBuy = entry.getKey();
            BigDecimal totalFee = entry.getValue();

            GetFeeRes feeRes = new GetFeeRes();
            Optional<User> user = userDAO.findById(userBuy);
            feeRes.setId(user.get().getId());
            feeRes.setFullName(user.get().getFullName());
            feeRes.setFeeAmount(totalFee);
            result.add(feeRes);
        }
        return result;
    }

    @Override
    public ApiResponse<?> getToltalFee(GetFeeReq request) {
        List<Transaction> tempTransaction = transactionDAO.findTransactionByDateTimeBetween(request.getFromDate(), request.getToDate());
        Map<Integer, BigDecimal> totalFeeUseByUser = new HashMap<>();
        Map<Integer, BigDecimal> totalFeeAmountByUserBuy = new HashMap<>();
        List<User> users = userDAO.findAll();

        for (User user : users) {
            totalFeeUseByUser.put(user.getId(), BigDecimal.ZERO);
        }

        for(Transaction transaction: tempTransaction) {
            Integer userBuy = transaction.getUserBuy();
            BigDecimal fee = totalFeeAmountByUserBuy.getOrDefault(userBuy, BigDecimal.ZERO);
            fee = fee.add(transaction.getFeeAmount());
            totalFeeAmountByUserBuy.put(userBuy, fee);
            for(User user: transaction.getUsers()) {
                totalFeeUseByUser.put(user.getId(), totalFeeUseByUser.getOrDefault(user.getId(), new BigDecimal(0)).add(transaction.getAverageAmount()));
            }
        }
        List<FeeUserDTO> result = new ArrayList<>();
        for(Map.Entry<Integer, BigDecimal> entry: totalFeeUseByUser.entrySet()){
            Integer userId = entry.getKey();
            BigDecimal feeUse = entry.getValue();
            Optional<User> user = userDAO.findById(userId);

            FeeUserDTO feeRes = new FeeUserDTO();
            feeRes.setId(user.get().getId());
            feeRes.setFullName(user.get().getFullName());
            feeRes.setFeeUse(feeUse);
            for(Map.Entry<Integer, BigDecimal> entryFee: totalFeeAmountByUserBuy.entrySet()) {
                if(userId == entryFee.getKey()) {
                    feeRes.setFeeAmount(entryFee.getValue());
                }
            }
            result.add(feeRes);
        }
        ApiResponse<List<FeeUserDTO>> res = new ApiResponse<>("00", result, "Thành công");
        return res;
    }

    @Transactional
    @Override
    public ApiResponse<String> updateTransaction(UpdateTransactionReq request) {
        Optional<Transaction> theTransaction = transactionDAO.findById(request.getId());
        theTransaction.get().setTitle(request.getTitle());
        theTransaction.get().setAverageAmount(request.getAverageAmount());
        theTransaction.get().setFeeAmount(request.getFeeAmount());
        theTransaction.get().setNote(request.getNote());
        theTransaction.get().setUserBuy(request.getUserBuy());
        theTransaction.get().setUsers(new ArrayList<>());
        for(Integer id: request.getLstUser()) {
            Optional<User> user = userDAO.findById(id);
            theTransaction.get().addUser(user.get());
        }
        transactionDAO.save(theTransaction.get());
        ApiResponse<String> response = new ApiResponse<>("00","", "Cập nhật thông tin giao dịch thành công");
        return response;
    }

    @Override
    public ApiResponse<?> detailTransaction(DetailUser request) {
        Optional<Transaction> theTransaction = transactionDAO.findById(request.getId());
        if(theTransaction.isEmpty()) {
            ApiResponse<String> res = new ApiResponse<>("11", "Không tìm thấy giao dịch");
            return res;
        } else {
            Optional<User> theUser = userDAO.findById(theTransaction.get().getUserBuy());
            ListTransactionRes transactionRes = new ListTransactionRes(theTransaction.get().getId(), theTransaction.get().getTitle(),theTransaction.get().getDateTime(), theTransaction.get().getAverageAmount(), theTransaction.get().getFeeAmount(), theTransaction.get().getNote(), "");
            transactionRes.setUserBuy(theUser.get().getFullName());
            for(User user:theTransaction.get().getUsers()) {
                transactionRes.add(user.getFullName());
            }
            ApiResponse<ListTransactionRes> res = new ApiResponse<>("00", transactionRes, "Thành công");
            return  res;
        }
    }
}
