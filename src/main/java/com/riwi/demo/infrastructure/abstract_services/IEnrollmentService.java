package com.riwi.demo.infrastructure.abstract_services;

import com.riwi.demo.api.dto.request.EnrollmentReq;
import com.riwi.demo.api.dto.response.EnrollmentsResp;

public interface IEnrollmentService extends CrudService<EnrollmentReq,EnrollmentsResp, String> {

}
