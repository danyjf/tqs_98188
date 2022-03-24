a)
	A_EmployeeRepositoryTest:
		assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
	B_EmployeeService_UnitTest:
		assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
	C_EmployeeController_WithMockServiceTest:
		
	D_EmployeeRestControllerIT:
		assertThat(found).extracting(Employee::getName).containsOnly("bob");
	E_EmployeeRestControllerTemplateIT:
		assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");

b)
	B_EmployeeService_UnitTest
	
c)
	The standard @Mock is used to create mock objects of classes or interfaces. The @MockBean annotation is used to add mock objects to the Spring application context.

d)
	The application-integrationtest.properties file contains a configuration for the database to be used in tests. This file will be used when running the application tests.
	
e)
	On test C the project demonstrates the use of the @WebMvcTest annotation with a MockMvc object, on test D is shown the use of @SpringBootTest annotation with a MockMvc object and on test E is also used @SpringBootTest but this time the api is accessed with a TestRestTemplate object.

