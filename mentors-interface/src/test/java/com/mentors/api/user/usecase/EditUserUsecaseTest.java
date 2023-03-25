package com.mentors.api.user.usecase;

import static com.mentors.api.user.mapper.UserApiMapper.toDomain;
import static com.mentors.support.fixture.UserFixture.회원정보수정_요청정보;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

import com.mentors.api.user.dto.UserEditRequest;
import com.mentors.support.UsecaseTest;
import com.mentors.user.service.UserWriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class EditUserUsecaseTest extends UsecaseTest {

    @InjectMocks
    private EditUserUsecase editUserUsecase;

    @Mock
    private UserWriteService userWriteService;


    @DisplayName("[Usecase] 유저 회원 정보 수정")
    @Test
    void givenUserIdAndUserEditRequest_whenUpdating_thenReturnVoid() {
        //given
        Long userId = 1L;
        UserEditRequest request = 회원정보수정_요청정보();

        //when & then
        doNothing().when(userWriteService).updateUser(userId, toDomain(request));
        assertDoesNotThrow(() -> editUserUsecase.execute(userId, request));
    }
}