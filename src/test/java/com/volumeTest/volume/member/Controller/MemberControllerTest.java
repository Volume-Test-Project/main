package com.volumeTest.volume.member.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;
import com.volumeTest.volume.member.mapper.MemberMapper;
import com.volumeTest.volume.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
//@Transactional
@WebMvcTest(MemberController.class)
class MemberControllerTest {

  @Autowired
  MemberController memberController;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MemberService memberService;

  @MockBean
  private MemberMapper memberMapper;

  @Test
  @DisplayName("회원 가입 성공 테스트")
  @WithMockUser(username = "test", roles = "USER")
  void postMember() throws Exception {
    // given
    MemberDto.Post post = new MemberDto.Post("test@gmail.com","a123456!#", "김테스트");
    Member member = new Member(1, post.getEmail(), post.getPassword(), post.getName());

    given(memberMapper.memberPostDtoToMember(any(MemberDto.Post.class))).willReturn(member);
    given(memberService.createMember(any(Member.class))).willReturn(member);

    // when & then
    mockMvc.perform(post("/member/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(post))
                    .with(csrf()))
            .andExpect(status().isCreated());

    then(memberService).should().createMember(any(Member.class));
  }

  @Test
  void getMember() {
  }

  @Test
  void patchMember() {
  }

  @Test
  void deleteMember() {
  }
}