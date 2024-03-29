package pl.pollub.modefactory.posestrategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static java.awt.event.KeyEvent.VK_LEFT;

class KeyActionContextTest {

    private KeyActionContext testedContext;
    private KeyActionContext mockTestedContext;
    private KeyActionStrategy mockLeftKey;

    @BeforeEach
    void setUp() {
        testedContext = new KeyActionContext();
        mockTestedContext = Mockito.mock(KeyActionContext.class);
        mockLeftKey = Mockito.mock(LeftKeyAction.class);
    }

    @AfterEach
    void tearDown() {
        testedContext = null;
        mockLeftKey = null;
    }

    @Test
    public void shouldSetChoosenStrategy() {

        //given
        Class<? extends KeyActionStrategy> aClass = mockLeftKey.getClass();

        //when
        testedContext.set(mockLeftKey);

        //then
        Assertions.assertEquals(LeftKeyAction.class, testedContext.getStrategy().getClass());
        Assertions.assertNotEquals(RightKeyAction.class, testedContext.getStrategy().getClass());
        Assertions.assertEquals(aClass, testedContext.getStrategy().getClass());
    }

    @Test
    public void shouldRunKeyEventStrategyMethod() {
        mockTestedContext.set(mockLeftKey);

        //when
        mockTestedContext.simulateKeyEvent();

        //then
        Mockito.verify(mockTestedContext).simulateKeyEvent();
    }

    @Test
    public void shouldReturnValueOfPressedKey() {
        testedContext.set(new LeftKeyAction());

        //when
        Mockito.when(mockTestedContext.simulateKeyEvent()).thenReturn(VK_LEFT);
        int expectedResult = VK_LEFT;
        int actualResult = testedContext.simulateKeyEvent();

        //then
        Assertions.assertEquals(expectedResult, actualResult);
    }
}