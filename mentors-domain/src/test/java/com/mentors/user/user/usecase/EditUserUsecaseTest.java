package com.mentors.user.user.usecase;

import com.mentors.support.UsecaseTest;
import com.mentors.user.user.mapper.UserDomainMapper;
import com.mentors.user.user.service.UserWriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.mentors.UserFixture.toUserEditRequest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;

class EditUserUsecaseTest extends UsecaseTest {

    @InjectMocks
    private EditUserUsecase editUserUsecase;

    @Mock
    private UserWriteService userWriteService;


    @DisplayName("[Usecase] 유저 회원 정보 수정")
    @Test
    void givenUserIdAndUserEditRequest_whenUpdating_thenReturnVoid() {
        //given
        var userId = 1L;
        var request = toUserEditRequest();

        //when & then
        doNothing().when(userWriteService).updateUser(userId, UserDomainMapper.toDomain(request));
        assertDoesNotThrow(() -> editUserUsecase.execute(userId, request));
    }
}