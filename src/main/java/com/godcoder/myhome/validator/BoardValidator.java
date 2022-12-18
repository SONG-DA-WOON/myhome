package com.godcoder.myhome.validator;

import com.godcoder.myhome.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
// Validator 사용해서 제목이나, 내용이 없으면 못넘어가게 return 해주는 방법 자바스크립트 사용 안하고 자바에서 return 해주는 방법 (유용할거같다)
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        Board b = (Board) obj;
        if(StringUtils.isEmpty(b.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        }
    }
}
