package pl.pollub.modefactory.posestrategy;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;

public class LeftKeyActionTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private KeyActionStrategy mockLeftKey;
    private KeyActionStrategy mockRightKey;

    @Before
    public void setUp() {
        mockLeftKey = Mockito.mock(LeftKeyAction.class);
        mockRightKey = Mockito.mock(RightKeyAction.class);
    }

    @After
    public void tearDown() {
        mockLeftKey = null;
    }


    @Test
    public void shouldInvokeMethodPressLeftKey() {
        //given
        int keyCode = KeyEvent.VK_LEFT;

        //when
        Mockito.when(mockLeftKey.pressKey()).thenReturn(keyCode);
        int result = mockLeftKey.pressKey();

        //then
        Assert.assertEquals(keyCode, result);
        Mockito.verify(mockLeftKey).pressKey();
        Mockito.verifyZeroInteractions(mockRightKey);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceptionOnMethodPressMouseKey() throws IllegalArgumentException {
        //given
        Mockito.when(mockLeftKey.pressMouseKey()).thenThrow(new IllegalArgumentException());

        //when
        int result = mockLeftKey.pressMouseKey();

        //then
        Mockito.verify(mockLeftKey).pressMouseKey();
        Mockito.verifyZeroInteractions(mockLeftKey);
        Mockito.verifyZeroInteractions(mockRightKey);
    }

    @Test
    public void shouldPressLeftKey() {
        //given
        LeftKeyAction leftKeyAction = new LeftKeyAction();
        int expected = KeyEvent.VK_LEFT;

        //when
        int result = leftKeyAction.pressKey();

        //then
        Assert.assertEquals(expected, result);
    }
}