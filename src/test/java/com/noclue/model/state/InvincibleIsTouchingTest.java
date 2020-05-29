package com.noclue.model.state;

import com.noclue.model.Filler;
import com.noclue.model.character.HeroModel;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class InvincibleIsTouchingTest {

    @Test
    public void isTouching() {

        NormalIsTouching normalIsTouching = Mockito.mock(NormalIsTouching.class);
        NormalDeactivate normalDeactivate = Mockito.mock(NormalDeactivate.class);
        HeroModel heroModel = Mockito.mock(HeroModel.class);
        when(heroModel.getTouchCounter()).thenReturn(0);
        when(heroModel.getNormalDeactivate()).thenReturn(normalDeactivate);
        when(heroModel.getNormalIsTouching()).thenReturn(normalIsTouching);

        IsTouchingState isTouchingState = new InvincibleIsTouching(heroModel);
        Filler filler = Mockito.mock(Filler.class);

        isTouchingState.isTouching(filler);
        Mockito.verify(heroModel,times(1))
                .setDeactivateState(any(NormalDeactivate.class));
        Mockito.verify(heroModel,times(1))
                .setIsTouchingState(any(NormalIsTouching.class));
        Mockito.verify(heroModel,times(1))
                .setTouchCounter(-1);
        Mockito.verify(filler,times(1))
                .deactivate();


        when(heroModel.getTouchCounter()).thenReturn(1);
        isTouchingState.isTouching(filler);
        Mockito.verify(heroModel,times(1))
                .setDeactivateState(any());
        Mockito.verify(heroModel,times(1))
                .setIsTouchingState(any());
        Mockito.verify(heroModel,times(1))
                .setTouchCounter(0);
        Mockito.verify(filler,times(2))
                .deactivate();

        when(heroModel.getTouchCounter()).thenReturn(-1);
        isTouchingState.isTouching(filler);
        Mockito.verify(heroModel,times(2))
                .setDeactivateState(any());
        Mockito.verify(heroModel,times(2))
                .setIsTouchingState(any());
        Mockito.verify(heroModel,times(1))
                .setTouchCounter(-2);
        Mockito.verify(filler,times(3))
                .deactivate();
    }

    @Test
    public void activate() {
    }
}