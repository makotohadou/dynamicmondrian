package br.com.itecbrazil.mondrianitecspring.utils;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter{

   public void init(FilterConfig filterConfig) throws ServletException {
      // TODO Auto-generated method stub
      
   }

   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
       HttpServletResponse response = (HttpServletResponse) res;
       HttpServletRequest request = (HttpServletRequest) req;
       
       Enumeration<String> headersName = request.getHeaderNames();
       while(headersName.hasMoreElements()) {
          System.out.println(request.getHeader(headersName.nextElement()));
       }
       
       //Preflight request
       response.setHeader("Access-Control-Allow-Origin", "*");
       response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
       /*
        * https://stackoverflow.com/questions/32500073/request-header-field-access-control-allow-headers-is-not-allowed-by-itself-in-pr
        * You need to reply to that CORS preflight with the appropriate CORS headers to make this work. 
        * One of which is indeed Access-Control-Allow-Headers. 
        * That header needs to contain the same values the Access-Control-Request-Headers header contained (or more)
        */
       String allowHeaders = request.getHeader("access-control-request-headers");
       if(allowHeaders != null) {
          response.addHeader("Access-Control-Allow-Headers", allowHeaders);
       }else {
          response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, Authorization, Accept-Encoding");
       }

       response.addHeader("Access-Control-Max-Age", "1728000");
       response.addHeader("Connection", "keep-alive");
       
       if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
           response.setStatus(HttpServletResponse.SC_OK);
       } else {
           chain.doFilter(req, res);
       }
   }

   public void destroy() {
      // TODO Auto-generated method stub
      
   }


}
