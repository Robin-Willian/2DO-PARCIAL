package context;

import java.awt.Dimension;

import Data.Models.Product;
import i18n.Translations;
import ui.custom.BaseWindow;
import ui.custom.ButtonComponent;
import ui.custom.PanelBox;
import ui.custom.TextFieldComponent;

public class RegisterFormP extends BaseWindow {
    private TextFieldComponent nameField;
    private TextFieldComponent descriptionField;
    private TextFieldComponent typeField;
    private TextFieldComponent priceField;
    private ButtonComponent registerButton;
    private Translations lang;
    private PanelBox panel;

    public RegisterFormP(Translations lang, String title) {
        super(title);
        this.lang = lang;
        this.setSize(new Dimension(350, 400));

        createForm();
    }

    public void createForm() {
        panel = new PanelBox();
        panel.setSize(new Dimension(350, 400));
        panel.roundedBorder(20);
        panel.setLayout(null);

        nameField = new TextFieldComponent("Name Product", this.panel);
        nameField.setPosition(40);
        nameField.setSize(new Dimension(260, 40));
        nameField.setPlaceHolder("Name Product");
        this.panel.add(nameField);
        descriptionField = new TextFieldComponent("Description Product", this.panel);
        descriptionField.setPosition(80);
        descriptionField.setSize(new Dimension(260, 40));
        descriptionField.setPlaceHolder("Description Product");
        this.panel.add(descriptionField);
        typeField = new TextFieldComponent("Type Product", this.panel);
        typeField.setPosition(120);
        typeField.setSize(new Dimension(260, 40));
        this.panel.add(typeField);
        priceField = new TextFieldComponent("Price Product", this.panel);
        priceField.setPosition(160);
        priceField.setSize(new Dimension(260, 40));
        priceField.setPlaceHolder("Price Product");
        this.panel.add(priceField);
        registerButton = new ButtonComponent("Register Your Product", this.panel);
        registerButton.setPosition(200);
        registerButton.setSize(new Dimension(260, 40));
        registerButton.addActionListener((event) -> {
            Product Product = new Product(this.nameField.getText(), this.descriptionField.getText(),
                    this.typeField.getText(), this.priceField.getText());
            this.getListener().onEvent("click-register", Product);
        });
        this.panel.add(registerButton);
        this.add(this.panel);
    }
}
