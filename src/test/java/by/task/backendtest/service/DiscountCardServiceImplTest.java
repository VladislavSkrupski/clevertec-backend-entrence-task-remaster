package by.task.backendtest.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.task.backendtest.DAO.DiscountCardRepository;
import by.task.backendtest.store.discountCard.DiscountCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DiscountCardServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DiscountCardServiceImplTest {
    @MockBean
    private DiscountCardRepository discountCardRepository;

    @Autowired
    private DiscountCardServiceImpl discountCardServiceImpl;

    /**
     * Method under test: {@link DiscountCardServiceImpl#getById(int)}
     */
    @Test
    void testGetById() {
        DiscountCard discountCard = new DiscountCard(1, 3);

        when(discountCardRepository.findById(anyInt())).thenReturn(discountCard);
        assertSame(discountCard, discountCardServiceImpl.getById(1));
        verify(discountCardRepository).findById(anyInt());
    }
}

