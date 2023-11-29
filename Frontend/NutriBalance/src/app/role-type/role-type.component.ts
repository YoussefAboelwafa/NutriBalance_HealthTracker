import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-role-type',
  templateUrl: './role-type.component.html',
  styleUrls:[ './role-type.component.css']
})
export class RoleTypeComponent {
selected_role:string='';
constructor(private router: Router) {}

 onSelection(){
   if(this.selected_role=='coach') this.router.navigate(['/coach-signup']);
}
}
