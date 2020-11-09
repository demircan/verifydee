package com.govern.studentdetech.ws;

import org.springframework.http.HttpStatus;

public class ResponseBeanWrapped<T> {

   T response;

   HttpStatus status;
   
   public ResponseBeanWrapped() {
      super();
   }
   
   
   public ResponseBeanWrapped(T response, HttpStatus status) {
      super();
      this.response = response;
      this.status = status;
   }

   public T getResponse() {
      return response;
   }

   public void setResponse(T response) {
      this.response = response;
   }

   public HttpStatus getStatus() {
      return status;
   }

   public void setStatus(HttpStatus status) {
      this.status = status;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(" response:");
      builder.append(response);
      builder.append(", status:");
      builder.append(status);
      return builder.toString();
   }


}
