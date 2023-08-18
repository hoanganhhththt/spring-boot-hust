package com.example.project.service;

import com.example.project.dao.TransactionRepository;
import com.example.project.dao.UserRepository;
import com.example.project.entity.TransactionDTO;
import com.example.project.entity.UserDTO;
import com.example.project.request.GetFeeReq;
import com.example.project.request.ListTransactionReq;
import com.example.project.request.TransactionAndUsersReq;
import com.example.project.request.UpdateTransactionReq;
import com.example.project.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository theTransactionRepository, UserRepository theUserRepository) {
        transactionRepository = theTransactionRepository;
        userRepository = theUserRepository;
    }
    @Override
    public List<TransactionDTO> findAllTransaction() {
        return transactionRepository.findAll();
    }

    @Transactional
    @Override
    public TransactionDTO save(TransactionDTO theTransactionDTO) {
        return transactionRepository.save(theTransactionDTO);
    }

    @Override
    public TransactionDTO findTransactionById(int theId) {
        Optional<TransactionDTO> result = transactionRepository.findById(theId);
        TransactionDTO theTransactionDTO = null;
        if(result.isPresent()) {
            theTransactionDTO = result.get();
        } else{
            throw new RuntimeException("Did not find transaction id -" + theId);
        }
        return theTransactionDTO;
    }

    @Override
    public ApiResponse<?> findByUserBuyAndDateTimeBetween(ListTransactionReq request) {

        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<TransactionDTO> response = transactionRepository.findTransactionByUserBuyAndDateTimeBetween(request.getUserBuy(),request.getFromDate(), request.getToDate(), pageable);;;
        if(request.getUserBuy() == 0) {
            response = transactionRepository.findTransactionByDateTimeBetween(request.getFromDate(), request.getToDate(), pageable);
        }
        if(response.getContent().isEmpty()) {
            ApiResponse<String> res = new ApiResponse<>("00", "", "Thành công");
            return res;
        } else {
            List<ListTransactionRes> result = new ArrayList<>();
            for(TransactionDTO transactionDTO : response) {
                ListTransactionRes tempRes = new ListTransactionRes(transactionDTO.getId(),
                        transactionDTO.getTitle(),
                        transactionDTO.getDateTime(),
                        transactionDTO.getAverageAmount(),
                        transactionDTO.getFeeAmount(),
                        transactionDTO.getNote(),
                        null);
                Optional<UserDTO> userBuy = userRepository.findById(transactionDTO.getUserBuy());
                tempRes.setUserBuy(userBuy.get().getFullName());
                for(UserDTO tempUserDTO : transactionDTO.getUsers()) {
                    tempRes.add(tempUserDTO.getFullName());
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
        List<TransactionDTO> tempTransactionDTO = transactionRepository.findTransactionByDateTimeBetween(request.getFromDate(), request.getToDate());
        Map<Integer, BigDecimal> totalFeeUseByUser = new HashMap<>();
        List<UserDTO> userDTOS = userRepository.findAll();

        for (UserDTO userDTO : userDTOS) {
            totalFeeUseByUser.put(userDTO.getId(), BigDecimal.ZERO);
        }

        for(TransactionDTO transactionDTO : tempTransactionDTO) {
            for(UserDTO userDTO : transactionDTO.getUsers()) {
                totalFeeUseByUser.put(userDTO.getId(), totalFeeUseByUser.getOrDefault(userDTO.getId(), new BigDecimal(0)).add(transactionDTO.getAverageAmount()));
            }
        }
        List<GetFeeRes> result = new ArrayList<>();
        for(Map.Entry<Integer, BigDecimal> entry: totalFeeUseByUser.entrySet()){
            Integer userId = entry.getKey();
            BigDecimal feeAmount = entry.getValue();
            Optional<UserDTO> user = userRepository.findById(userId);

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
        List<TransactionDTO> transactionDTOS = transactionRepository.findTransactionByDateTimeBetween(request.getFromDate(), request.getToDate());
        List<UserDTO> userDTOS = userRepository.findAll();
        Map<Integer, BigDecimal> totalFeeByUserBuy = new HashMap<>();
        for (UserDTO userDTO : userDTOS) {
            totalFeeByUserBuy.put(userDTO.getId(), BigDecimal.ZERO);
        }
        for (TransactionDTO transactionDTO : transactionDTOS) {
            Integer userBuy = transactionDTO.getUserBuy();
            BigDecimal fee = totalFeeByUserBuy.getOrDefault(userBuy, BigDecimal.ZERO);
            fee = fee.add(transactionDTO.getFeeAmount());
            totalFeeByUserBuy.put(userBuy, fee);
        }
        List<GetFeeRes> result = new ArrayList<>();
        for(Map.Entry<Integer, BigDecimal> entry: totalFeeByUserBuy.entrySet() ) {
            Integer userBuy = entry.getKey();
            BigDecimal totalFee = entry.getValue();

            GetFeeRes feeRes = new GetFeeRes();
            Optional<UserDTO> user = userRepository.findById(userBuy);
            feeRes.setId(user.get().getId());
            feeRes.setFullName(user.get().getFullName());
            feeRes.setFeeAmount(totalFee);
            result.add(feeRes);
        }
        return result;
    }

    @Override
    public ApiResponse<?> getToltalFee(GetFeeReq request) {
        List<TransactionDTO> tempTransactionDTO = transactionRepository.findTransactionByDateTimeBetween(request.getFromDate(), request.getToDate());
        Map<Integer, BigDecimal> totalFeeUseByUser = new HashMap<>();
        Map<Integer, BigDecimal> totalFeeAmountByUserBuy = new HashMap<>();
        List<UserDTO> userDTOS = userRepository.findAll();

        for (UserDTO userDTO : userDTOS) {
            totalFeeUseByUser.put(userDTO.getId(), BigDecimal.ZERO);
        }

        for(TransactionDTO transactionDTO : tempTransactionDTO) {
            Integer userBuy = transactionDTO.getUserBuy();
            BigDecimal fee = totalFeeAmountByUserBuy.getOrDefault(userBuy, BigDecimal.ZERO);
            fee = fee.add(transactionDTO.getFeeAmount());
            totalFeeAmountByUserBuy.put(userBuy, fee);
            for(UserDTO userDTO : transactionDTO.getUsers()) {
                totalFeeUseByUser.put(userDTO.getId(), totalFeeUseByUser.getOrDefault(userDTO.getId(), new BigDecimal(0)).add(transactionDTO.getAverageAmount()));
            }
        }
        List<FeeUserDTO> result = new ArrayList<>();
        for(Map.Entry<Integer, BigDecimal> entry: totalFeeUseByUser.entrySet()){
            Integer userId = entry.getKey();
            BigDecimal feeUse = entry.getValue();
            Optional<UserDTO> user = userRepository.findById(userId);

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
        Optional<TransactionDTO> theTransaction = transactionRepository.findById(request.getId());
        theTransaction.get().setTitle(request.getTitle());
        theTransaction.get().setAverageAmount(request.getAverageAmount());
        theTransaction.get().setFeeAmount(request.getFeeAmount());
        theTransaction.get().setNote(request.getNote());
        theTransaction.get().setUserBuy(request.getUserBuy());
        theTransaction.get().setUsers(new ArrayList<>());
        for(Integer id: request.getLstUser()) {
            Optional<UserDTO> user = userRepository.findById(id);
            theTransaction.get().addUser(user.get());
        }
        transactionRepository.save(theTransaction.get());
        ApiResponse<String> response = new ApiResponse<>("00","", "Cập nhật thông tin giao dịch thành công");
        return response;
    }

    @Override
    public ApiResponse<?> detailTransaction(DetailUser request) {
        Optional<TransactionDTO> theTransaction = transactionRepository.findById(request.getId());
        if(theTransaction.isEmpty()) {
            ApiResponse<String> res = new ApiResponse<>("11", "Không tìm thấy giao dịch");
            return res;
        } else {
            Optional<UserDTO> theUser = userRepository.findById(theTransaction.get().getUserBuy());
            ListTransactionRes transactionRes = new ListTransactionRes(theTransaction.get().getId(), theTransaction.get().getTitle(),theTransaction.get().getDateTime(), theTransaction.get().getAverageAmount(), theTransaction.get().getFeeAmount(), theTransaction.get().getNote(), "");
            transactionRes.setUserBuy(theUser.get().getFullName());
            for(UserDTO userDTO :theTransaction.get().getUsers()) {
                transactionRes.add(userDTO.getFullName());
            }
            ApiResponse<ListTransactionRes> res = new ApiResponse<>("00", transactionRes, "Thành công");
            return  res;
        }
    }

    @Transactional
    @Override
    public void addTransactionAndUsers(TransactionAndUsersReq request) {
        BigDecimal averageAmount = request.getFeeAmount().divide(BigDecimal.valueOf(request.getLstUser().size()),0,BigDecimal.ROUND_HALF_UP);
        // tạo mới Transaction
        TransactionDTO tempTransactionDTO = new TransactionDTO(request.getTitle(),
                Timestamp.valueOf(LocalDateTime.now()),
                averageAmount,
                request.getFeeAmount(),
                request.getUserBuy(),
                request.getNote()
        );

        List<Integer> lstUser = request.getLstUser();
        // Lọc từng phần tử user để thêm vào Transacton có quan hệ N-N
        for(Integer user: lstUser) {
            UserDTO tempUserDTO = userRepository.findById(user).get();
            tempTransactionDTO.addUser(tempUserDTO);
        }
        transactionRepository.save(tempTransactionDTO);
    }
}
