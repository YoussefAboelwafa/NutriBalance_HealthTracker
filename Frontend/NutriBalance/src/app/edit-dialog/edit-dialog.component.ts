import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';

import { MatSelectModule } from '@angular/material/select';


@Component({
  selector: 'app-edit-dialog',
  templateUrl: './edit-dialog.component.html',
  styleUrls: ['./edit-dialog.component.css'],
})
export class EditDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<EditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  compareGoal(option1: any, option2: any): boolean {
    return option1 && option2 ? option1.value === option2.value : option1 === option2;
  }
  

  goalOptions = [
    { label: 'Cut', value: 'cut' },
    { label: 'Bulk', value: 'bulk' },
    // Add more options as needed
  ];

  onCancel(): void {
    this.dialogRef.close("cancel");
  }

  onSubmit(): void {
    this.dialogRef.close("submit");
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
