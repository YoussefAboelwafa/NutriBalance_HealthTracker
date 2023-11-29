import { Component } from '@angular/core';
import { ModalPopServiceService } from '../_services/modal-pop-service.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-fp-popup',
  templateUrl: './fp-popup.component.html',
  styleUrls: ['./fp-popup.component.css']
})
export class FpPopupComponent {
  inputValue: string = '';

  constructor(private dialog: MatDialog) {}

  openPopup() {
    const dialogRef = this.dialog.open(FpPopupComponent, {
      width: '400px', // Adjust the width as needed
    });

    dialogRef.afterClosed().subscribe((result) => {
      // Handle the result when the popup is closed (if needed)
    });
  }
  onSubmit() {
    // Add your logic for handling form submission here
    // You can access the input value using this.inputValue

    // Close the popup
    this.dialog.closeAll();
  }

  onClose() {
    // Close the popup without taking any action
    this.dialog.closeAll();
  }
}