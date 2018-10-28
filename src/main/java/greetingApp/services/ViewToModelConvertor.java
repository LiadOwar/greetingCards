package greetingApp.services;

import greetingApp.greetingCardData.AbstractGreetingCardData;
import greetingApp.viewmodel.GreetingCardViewModel;

/**
 * Created by liadm on 28/10/2018.
 */
public interface ViewToModelConvertor {
    AbstractGreetingCardData convertGreetingCardViewModel(GreetingCardViewModel view);
}
