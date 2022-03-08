import {FormGroup} from "@angular/forms";

export function crossFieldValidator(controlName: string, matchingControlName: string) {
  return (formGroup: FormGroup) => {
    let control = formGroup.controls[controlName];
    let matchingControl = formGroup.controls[matchingControlName]
    if (
      matchingControl.errors &&
      !matchingControl.errors.crossFieldValidator
    ) {
      return;
    }
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({ crossFieldValidator: true });
    } else {
      matchingControl.setErrors(null);
    }
  };
}
