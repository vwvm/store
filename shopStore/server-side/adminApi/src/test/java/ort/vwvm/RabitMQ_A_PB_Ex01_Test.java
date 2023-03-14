package ort.vwvm;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vwvm.AdminApiApplication;
import org.vwvm.store.beans.adminBean.Member;
import org.vwvm.store.services.adminService.IMemberService;


@SpringBootTest(classes = AdminApiApplication.class)
public class RabitMQ_A_PB_Ex01_Test {
    @Autowired
    private AmqpAdmin amqpAdmin;


    @Test
    public void ex01_admin() {
// 1、定义fanout类型的交换机
        amqpAdmin.declareExchange(new FanoutExchange("PB_Ex01"));
// 2、定义两个默认持久化队列，分别处理email和sms
        amqpAdmin.declareQueue(new Queue("PB_Ex01_A"));
        amqpAdmin.declareQueue(new Queue("PB_Ex01_B"));
// 3、将队列分别与交换机进行绑定
        amqpAdmin.declareBinding(new
                Binding("PB_Ex01_A", Binding.DestinationType.QUEUE, "PB_Ex01", "", null));
        amqpAdmin.declareBinding(new
                Binding("PB_Ex01_B", Binding.DestinationType.QUEUE, "PB_Ex01", "", null));
    }

    @Test
    void memberTest(){
        System.out.println(memberService.getById(1L));
    }

    @Autowired
    private IMemberService memberService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 1、Publish/Subscribe工作模式消息发送端
     */
    @Test
    public void ex01_publish() {
        Member bean =memberService.getById(1L);
        rabbitTemplate.convertAndSend("PB_Ex01","", bean);
    }

    /**
     * 2、Publish/Subscribe工作模式消息接收端
     */
    @Test
    public void ex01_subscribe_1() {
        Object obj = rabbitTemplate.receiveAndConvert("PB_Ex01_A");
        System.out.println(obj);
    }
    /**
     * 2、Publish/Subscribe工作模式消息接收端
     */
    @Test
    public void ex01_subscribe_2() {
        Object obj = rabbitTemplate.receiveAndConvert("PB_Ex01_B");
        System.out.println(obj);
    }


}
