package in.kazu.restapi.service;

import in.kazu.restapi.dto.ExpenseDTO;

import java.util.List;

/**
 * service interface for expense module
 * @author  Kazu Ishihara
 */
public interface ExpenseService {
    /**
     * It will fetch expenses from the database
     * @return a list of expenseDTO
     */
    List<ExpenseDTO> getAllExpenses();
}
