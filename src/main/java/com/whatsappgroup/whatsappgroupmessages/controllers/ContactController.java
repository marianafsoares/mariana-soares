package com.whatsappgroup.whatsappgroupmessages.controllers;

import com.whatsappgroup.whatsappgroupmessages.controllers.exception.BaseResponse;
import com.whatsappgroup.whatsappgroupmessages.dtos.ContactDTO;
import com.whatsappgroup.whatsappgroupmessages.dtos.GroupDTO;
import com.whatsappgroup.whatsappgroupmessages.services.IContactService;
import com.whatsappgroup.whatsappgroupmessages.utils.ResponseCodes;
import com.whatsappgroup.whatsappgroupmessages.utils.ResponseFactory;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    IContactService contactService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "Return contact by id ", response = ContactDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[201 - \"The contact does not exist\"]")})
    @GetMapping(value = "/{contact_id}",produces = "application/json")
    public ContactDTO getContactById(@ApiParam(value = "contact_id", required = true) @PathVariable Long contact_id){
        return contactService.findByContactId(contact_id);
    }

    @ApiOperation(value = "Return contact by cellphone number ", response = ContactDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[201 - \"The contact does not exist\"]")})
    @GetMapping(value="/byCellPhone", produces = "application/json")
    public ContactDTO getContactByCellPhoneNumber(@ApiParam(value = "cellphone_number", required = true) @RequestParam String cellphone_number){
        return contactService.findByCellPhoneNumber(cellphone_number);
    }

    @ApiOperation(value = "Return contact by alias ", response = ContactDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[201 - \"The contact does not exist\"]")})
    @GetMapping(value="/byAlias", produces = "application/json")
    public ContactDTO getContactByAlias(@ApiParam(value = "alias", required = true) @RequestParam String alias){
        return contactService.findByAlias(alias);
    }

    @ApiOperation(value = "Return all contacts", response = ContactDTO.class, responseContainer="List")
    @GetMapping(produces = "application/json")
    public Set<ContactDTO> getAllContacts(){
        Set<ContactDTO> contactDTOList = contactService.getAll();
        return contactDTOList;
    }

    @ApiOperation(value = "Create a new contact", response = GroupDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "[\n202 - \"Failed to create a contact \", \n"
    )})
    @PostMapping(consumes = "application/json", produces = "application/json")
    public BaseResponse save (@Valid @RequestBody ContactDTO contactDTO){
        BaseResponse baseResponse;
        contactService.save(contactDTO);
        baseResponse = ResponseFactory.createResponse(ResponseCodes.CONTACT_SAVE_OK);

        return baseResponse;
    }


}
