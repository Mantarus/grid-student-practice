package com.gridstudentpractice.chatservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gridstudentpractice.chatservice.model.MessageDto;
import com.gridstudentpractice.chatservice.service.MessageService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MessageRestController.class)
class ResponseBodyTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean(name = "DBMessageServiceImpl")
    private MessageService messageService;


    private MessageDto invalidMessage () {
        return MessageDto.builder().sender("").chatroom("").body("").build();
    }

    private MessageDto validMessage() {
        return MessageDto.builder().sender("Mock").chatroom("Test").body("MessageDto").build();
    }

//    @Test
//    public void whenMessageIsInvalid_thenReturnStatus400() throws Exception {
//        MessageDto message = invalidMessage();
//        String body = objectMapper.writeValueAsString(message);
//        Mockito.when(messageService.sendMessage(Mockito.any(MessageDto.class)))
//                .thenReturn(message);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/restChat")
//                .accept(MediaType.APPLICATION_JSON)
//                .content(body)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
//    }

//    @Test
//    public void whenMessageIsValid_thenReturnStatus200() throws Exception {
//        MessageDto message = validMessage();
//        String body = objectMapper.writeValueAsString(message);
//        Mockito.when(messageService.sendMessage(Mockito.any(MessageDto.class)))
//                .thenReturn(message);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/restChat")
//                .accept(MediaType.APPLICATION_JSON)
//                .content(body)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//    }
}
