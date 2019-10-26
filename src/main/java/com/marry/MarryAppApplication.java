package com.marry;

import com.marry.common.domain.request.SysMemberChat;
import com.marry.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableAsync
public class MarryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarryAppApplication.class, args);
       /* try {
            new NettyChatServer(12345).start();
        }catch(Exception e) {
            System.out.println("NettyChatServerError:"+e.getMessage());
        }*/
        ArrayList<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);

        ArrayList<Integer> chats = new ArrayList<>();
        chats.add(1);
        chats.add(2);
        chats.add(3);

        List<Integer> collect = array.stream().filter(chat ->
                !chats.contains(chat)).collect(Collectors.toList());

        System.out.println(collect);
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println(filtered);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }
}
