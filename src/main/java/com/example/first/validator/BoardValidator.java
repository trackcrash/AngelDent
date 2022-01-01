package com.example.first.validator;

import com.example.first.model.board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return board.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        board b = (board) target;
        if(StringUtils.isEmpty(b.getContents())){
            errors.rejectValue("contents", "required", "내용을 입력해주세요.");
        }
    }
}
