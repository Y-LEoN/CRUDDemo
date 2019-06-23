package cn.wolfcode.crud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
    protected String encodingName;   

    protected FilterConfig sysFilter;   

    public void destroy() {   

    }   

    public void doFilter(ServletRequest req, ServletResponse res,   
            FilterChain arg2) throws IOException, ServletException {   
            try {     
                req.setCharacterEncoding(this.encodingName);   
                res.setContentType("text/html;charset=" + this.encodingName);   
                res.setCharacterEncoding(this.encodingName);   
                arg2.doFilter(req, res);   
            } catch (Exception e) {   
            //....
            }  finally{
//                 arg2.doFilter(req, res);   
            }
    }   

    public void init(FilterConfig arg0) throws ServletException {   
        this.sysFilter = arg0;   
        this.encodingName = this.sysFilter.getInitParameter("encoding");   
    }   

}
