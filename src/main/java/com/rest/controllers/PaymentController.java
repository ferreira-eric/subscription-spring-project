package com.rest.controllers;

import com.rest.api.PaymentAPI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController implements PaymentAPI {

    //quando pagamento acontecer colocar subscription para purchaded
}
