package com.gujun.springboot02.Interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstInterceptor implements HandlerInterceptor {

    /*
        拦截器：
        对于DispatcherServlet，它会根据HandlerMapping找到处理器，然后返回HandlerExecutionChain对象；
        该对象包含处理器和拦截器，拦截器会对处理器进行拦截，来起到增强处理器的功能；

        所有的拦截器都需要实现HandlerInterceptor接口(都是default修饰的空实现)；
        boolean preHandle()处理器执行前方法；
        void postHandle()处理器处理后方法；
        void afterCompletion()处理器完成后方法；
        流程：
        1.preHandle()，返回false则结束所有流程，返回true则执行下一步；
        2.处理器处理（包含控制器功能）；
        3.postHandle();
        4.执行视图解析和渲染;
        5.afterCompletion();

        多个拦截器的执行顺序：
        preHandle是先注册先执行，而postHandle、afterCompletion是先注册后执行；
        如果其中某天preHandle返回false,则后续的拦截器、处理器、所有的postHandle()都不会执行；afterCompletion()则不同，他会执行拦截器中preHandle()
        返回true的afterCompletion()；

     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("处理器执行前方法,"+request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("处理器执行后方法,"+request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("处理器完成后方法,"+request.getRequestURL());
    }

}
