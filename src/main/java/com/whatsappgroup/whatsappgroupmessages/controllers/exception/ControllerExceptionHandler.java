package com.whatsappgroup.whatsappgroupmessages.controllers.exception;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.contact.ContactNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.contact.ContactSaveException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.group.GroupNotExistException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.group.GroupSaveException;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.message.*;
import com.whatsappgroup.whatsappgroupmessages.controllers.exception.notification.*;
import com.whatsappgroup.whatsappgroupmessages.utils.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<BaseResponse> handleException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors()
                .stream()
                .map(this::mapError)
                .collect(Collectors.toList());
    }

    private BaseResponse mapError(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            return new BaseResponse(400, ((FieldError) objectError).getField() +" " +
                    objectError.getDefaultMessage());
        }
        return new BaseResponse(400, objectError.getObjectName() +" "+ objectError.getDefaultMessage());
    }


    @ResponseBody
    @ExceptionHandler(GroupNotExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse groupNotExistException(GroupNotExistException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(GroupSaveException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse groupSaveException(GroupSaveException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(ContactSaveException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse contactSaveException(ContactSaveException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(ContactNotExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse contactNotExistException(ContactNotExistException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(MessageNotExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse messageNotExistException(MessageNotExistException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(MessagesByContactAndGroupNotExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse messagesNotExistException(MessagesByContactAndGroupNotExistException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(MessagesByGroupNotExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse MessagesByGroupNotExistException(MessagesByGroupNotExistException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(MessageSaveException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse messageSaveException(MessageSaveException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(NotificationSaveException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse notificationSaveException(NotificationSaveException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(NotificationNotExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse notificationNotExistException(NotificationNotExistException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(NotificationByGroupNotExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse notificationByGroupNotExistException(NotificationByGroupNotExistException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(NotificationDeleteException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse notificationDeleteException(NotificationDeleteException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(NotificationDeleteByGroupException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse notificationDeleteByGroupException(NotificationDeleteByGroupException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(MessageDeleteException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse messageDeleteException(MessageDeleteException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }

    @ResponseBody
    @ExceptionHandler(MessageDeleteByGroupException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse messageDeleteByGroupException(MessageDeleteByGroupException ex){
        return ResponseFactory.createResponse(ex.getResponseCode());
    }
}
