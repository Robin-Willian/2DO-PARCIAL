package controller;

import i18n.Translations;
import context.Login;
import context.RegisterForm;
import context.RegisterFormP;
import Data.UserManager;
import Data.Models.User;
import Data.Models.Product;
import context.DashBoard;
import java.lang.Object;

import config.Evironments;
import context.ViewProducts;
import Data.ProductManager;

public class MainController {
    UserManager userManager = new UserManager(Evironments.pathFile);
    ProductManager productManager = new ProductManager(Evironments.pathFileProducts);
    Translations translations;
    Login login;
    DashBoard dashBoard;
    RegisterForm registerForm;
    RegisterFormP registerFormP;
    ViewProducts viewProducts;

    public MainController() {
        System.out.println("MainController");
        translations = new Translations();
        login = new Login(translations, translations.getI18nText("title-text"));
        viewProducts = new ViewProducts(translations, translations.getI18nText("viewProducts"));
        dashBoard = new DashBoard(translations, translations.getI18nText("DashBoard"));
        registerForm = new RegisterForm(translations, translations.getI18nText("title-text"));
        registerFormP = new RegisterFormP(translations, translations.getI18nText("title-text"));
        dashBoard.setData(userManager.getUsers());
        viewProducts.setData(productManager.getproducts());

        login.addEventListener((String actionEvent, Object data) -> {
            if (actionEvent.equals("click-login")) {
                login.hideWindow();
                dashBoard.showWindow();
            } else if (actionEvent.equals("click-product")) {
                login.hideWindow();
                viewProducts.showWindow();
            }
        });

        dashBoard.addEventListener((String actionEvent, Object data) -> {
            if (actionEvent.equals("click-register")) {
                registerForm.showWindow();
            }
        });

        viewProducts.addEventListener((String actionEvent, Object data) -> {
            if (actionEvent.equals("click-register")) {
                registerFormP.showWindow();
            }
        });

        registerForm.addEventListener((String actionEvent, Object data) -> {
            User user = (User) data;
            if (actionEvent.equals("click-register")) {
                userManager.saveUser(user);
                dashBoard.refresh(user);
            }
        });

        registerFormP.addEventListener((String actionEvent, Object data) -> {
            Product product = (Product) data;
            if (actionEvent.equals("click-register")) {
                productManager.saveProduct(product);
                viewProducts.refresh(product);
            }
        });
        login.showWindow();
    }
}
