package in.kazu.restapi.controller;

import in.kazu.restapi.dto.ExpenseDTO;
import in.kazu.restapi.io.ExpenseResponse;
import in.kazu.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is controller class for Expense module
 * @author  Kazu Ishihara
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ExpenseController {

    private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);
    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;

    /**
     * This will fetch expenses from the database
     * @return list
     */
    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses(){
        log.info("API  Get /expenses  called");
      //call the service method
      //convert the expense DTO to Expense Response
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        log.info("Printing the data from the service {}", list);
        List<ExpenseResponse> response = list.stream().map(expenseDTO -> mapToExpesneResponse(expenseDTO)).collect(Collectors.toList());
      //return the list or return the response
        return response;

    }

    /**
     * Mapper method for converting expenseDTO object to expense response
     *
     * @return expense response
     */
    private ExpenseResponse mapToExpesneResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }
}
