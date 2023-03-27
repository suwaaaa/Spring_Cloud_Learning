package Consume.Service;

import org.springframework.stereotype.Service;

@Service
public interface XAService {

    String testSeataTransactionXA();

    String testXA1();

    String testXA2();
}
