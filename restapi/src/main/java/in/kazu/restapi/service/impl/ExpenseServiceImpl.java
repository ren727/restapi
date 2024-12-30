package in.kazu.restapi.service.impl;

import in.kazu.restapi.dto.ExpenseDTO;
import in.kazu.restapi.entity.ExpenseEntity;
import in.kazu.restapi.repository.ExpenseRepository;
import in.kazu.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

/**
 * service implementation for service module
 */
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger log = LoggerFactory.getLogger(ExpenseServiceImpl.class);
    private final ExpenseRepository expenseRepository;   //injected dependencies
    private final ModelMapper modelMapper;

    /**
     * It will fetch expenses from the database
     * @return a list of expenseDTO
     */
    @Override
    public List<ExpenseDTO> getAllExpenses() {
        //call the repository method
        List<ExpenseEntity> list = expenseRepository.findAll();   //fetch all the expenses from the database
        log.info("Printing data from the repository {}", list);
        //Convert the Entity object to DTO object
        List<ExpenseDTO> listOfExpenses = list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());
        //return the list
        return listOfExpenses;

    }

    /**
     * Mapper method to convert expense entity to expense DTO
     * param expenseEntity
     * return expenseDTO
     */
    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }
}

