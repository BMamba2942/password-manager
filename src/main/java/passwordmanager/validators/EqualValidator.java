package passwordmanager.validators;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.DefaultProperty;
import javafx.scene.control.TextInputControl;

@DefaultProperty(value = "icon")
public class EqualValidator extends ValidatorBase {
    public void setError(boolean error) {
        hasErrors.set(error);
    }

    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl) {
//            evalText();
        }
    }
}
