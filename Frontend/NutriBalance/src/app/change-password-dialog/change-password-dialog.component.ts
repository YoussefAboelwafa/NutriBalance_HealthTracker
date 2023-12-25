import { Component, OnInit,Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-change-password-dialog',
  templateUrl: './change-password-dialog.component.html',
  styleUrls: ['./change-password-dialog.component.css']
})
export class ChangePasswordDialogComponent implements OnInit {
currentPassword: any;
newPassword: any;
confirmPassword: any;

  constructor(
    public dialogRef: MatDialogRef<ChangePasswordDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}
  ngOnInit(): void {
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
  onSubmit() {
    //validate the passwords
    if (this.newPassword != this.confirmPassword) {
      alert("Passwords do not match")
      return
    }
    if (this.newPassword.length < 8) {
      alert("Password must be at least 8 characters")
      return
    }
    if (this.newPassword == this.currentPassword) {
      alert("New password must be different from current password")
      return
    }
    this.dialogRef.close({ currentPassword: this.currentPassword, newPassword: this.newPassword });
  }
  onKeyUp(event: any) {
    if (event.keyCode === 13) {
      this.onSubmit();
    }
  }

}
