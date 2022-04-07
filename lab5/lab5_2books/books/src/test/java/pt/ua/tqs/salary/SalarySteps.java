package pt.ua.tqs.salary;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SalarySteps {
    SalaryManager manager;

    @Given("the salary management system is initialized with the following data")
    public void the_salary_management_system_is_initialized_with_the_following_data(io.cucumber.datatable.DataTable table) {
        List<Employee> employees = new ArrayList<>();
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for(Map<String, String> columns : rows) {
            employees.add(new Employee(
                            Integer.parseInt(columns.get("id")),
                            columns.get("user"),
                            Float.parseFloat(columns.get("salary"))
                    ));
        }

        manager = new SalaryManager(employees);
    }

    @When("the boss increases the salary for the employee with id {int} by {int}%")
    public void the_boss_increases_the_salary_for_the_employee_with_id_by(Integer id, Integer increaseInPercent) {
        manager.increaseSalary(id, increaseInPercent);
    }

    @Then("the payroll for the employee with id {int} should display a salary of {float}")
    public void the_payroll_for_the_employee_with_id_should_display_a_salary_of(Integer id, Float salary) {
        Employee nominee = manager.getPayroll(id);
        assertThat(nominee.getSalary(), equalTo(salary));
    }
}
