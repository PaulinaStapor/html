//package p.stapor.userservice.service.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.MimeTypeUtils;
//import org.springframework.web.context.WebApplicationContext;
//import p.stapor.userservice.controller.UserController;
//import p.stapor.userservice.controller.dto.UserDto;
//import p.stapor.userservice.controller.exception.UserNotExistException;
//import p.stapor.userservice.model.UserEntity;
//import p.stapor.userservice.service.UserService;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = UserController.class)
//@AutoConfigureMockMvc
//@Import(UserControllerTest.Config.class)
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @MockBean
//    private UserService userService;
//
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    public static String asJsonString(final Object object) {
//        try {
//            return new ObjectMapper().writeValueAsString(object);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @TestConfiguration
//    protected static class Config {
//        @Bean
//        public UserService userService() {
//            return Mockito.mock(UserService.class);
//        }
//    }
//
//    @Test
//    public void getAllUsers_shouldPrintAndReturnOk() throws Exception {
//        UserEntity loki = new UserEntity(1L, "Loki", "Laufeyson", 1234L);
//        List<UserEntity> userEntityList = Arrays.asList(loki);
//
//        given(userService.getAllUsers()).willReturn(userEntityList);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/users")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", is(loki.getFirstName())))
//                .andReturn().getResponse().getContentAsString();
//    }
//
//    @Test
//    public void getAllUsers_whenReturnEmptyList_shouldReturnOk() throws Exception {
//        List<UserEntity> userEmptyList = new ArrayList<>();
//        when(userService.getAllUsers()).thenReturn(userEmptyList);
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/users")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        verify(userService, times(1)).getAllUsers();
//        verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void givenUsers_whenFindUserById_thenReturnUser() throws Exception {
//        UserEntity venom = new UserEntity(20L, "Venom", "Venom", 345L);
//
//        given(userService.getUserById(20L)).willReturn(venom);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/user/{id}", 20L)
//                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(20)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(venom.getFirstName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", is(venom.getLastName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userUniqueNumber", is(345)));
//    }
//
//    @Test
//    public void givenUsers_whenFindByIdAndThrowException_shouldReturnNotFound() throws Exception {
//        Long id = 2L;
//        given(userService.getUserById(id)).willThrow(UserNotExistException.class);
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/user/{id}", id)
//                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
//                .andExpect(status().isNotFound());
//
//        verify(userService, times(1)).getUserById(id);
//        verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void givenUsers_whenFindUserByLastName_thenReturnUser() throws Exception {
//        Long id = 2L;
//        String firstName = "Iron";
//        String lastName = "Man";
//        Long ironManUniqueNumber = 234L;
//        UserEntity iron = new UserEntity(id, firstName, lastName, ironManUniqueNumber);
//        List<UserEntity> marvelList = new ArrayList<>();
//        marvelList.add(iron);
//
//        given(userService.getUsersByLastName(lastName)).willReturn(marvelList);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/users/{lastName}", lastName)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", is(firstName)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", is(lastName)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userUniqueNumber", is(234)));
//    }
//
//    @Test
//    public void givenUsers_whenFindByLastNameReturnEmptyList_shouldReturnOk() throws Exception {
//        String lastName = "batman";
//        List<UserEntity> batmanList = new ArrayList<>();
//        given(userService.getUsersByLastName(lastName)).willReturn(batmanList);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/users/{lastName}", lastName)
//                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void createUser_shouldReturnCreate() throws Exception {
//        String uri = "/users";
//        UserDto nickDto = new UserDto();
//        nickDto.setFirstName("Nick");
//        nickDto.setLastName("Fury");
//        nickDto.setUserUniqueNumber(123L);
//        UserEntity nick = new UserEntity(1L, nickDto.getFirstName(), nickDto.getLastName(), nickDto.getUserUniqueNumber());
//
//        doNothing().when(userService).addUser(nickDto);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post(uri)
//                .content(asJsonString(nick))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }
//
//
//    @Test
//    public void modifyUser_shouldReturnNoContent() throws Exception {
//        String uri = "/users/{id}";
//        Long id = 2L;
//        UserDto widow = new UserDto();
//        widow.setFirstName("Black");
//        widow.setLastName("Widow");
//        widow.setUserUniqueNumber(666L);
//        UserEntity newWidow = new UserEntity(id, widow.getFirstName(), widow.getLastName(), widow.getUserUniqueNumber());
//
//        doNothing().when(userService).modifyUser(id, widow);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .put(uri, id)
//                .content(asJsonString(newWidow))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent())
//                .andReturn().getResponse().getContentAsString();
//    }
//
//    @Test
//    public void modifyUser_whenThrowsException_shouldReturnNoContent() throws Exception {
//
//        String uri = "/users/{id}";
//        Long id = 3L;
//        UserDto captain = new UserDto();
//        captain.setFirstName("Captain");
//        captain.setLastName("America");
//        captain.setUserUniqueNumber(222L);
//        UserEntity newCaptain = new UserEntity(id, captain.getFirstName(), captain.getLastName(), captain.getUserUniqueNumber());
//
//        doThrow(UserNotExistException.class).when(userService).modifyUser(id, captain);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .put(uri, id)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(asJsonString(newCaptain)))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void whenDeleteUser_shouldReturnNoContent() throws Exception {
//        UserEntity daenerys = new UserEntity(2L, "Daenerys", "Targaryen", 555L);
//
//        doNothing().when(userService).deleteUser(daenerys.getId());
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/users/{id}", daenerys.getId())
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void whenDeleteUser_shouldReturnNotFound() throws Exception {
//        UserEntity cersei = new UserEntity(2L, "Cersei", "Lannister", 111L);
//
//        doThrow(UserNotExistException.class).when(userService).deleteUser(cersei.getId());
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/users/{id}", cersei.getId())
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//}
