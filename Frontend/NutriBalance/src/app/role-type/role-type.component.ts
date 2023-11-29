import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Shared } from '../common/shared';
@Component({
  selector: 'app-role-type',
  templateUrl: './role-type.component.html',
  styleUrls:[ './role-type.component.css']
})
export class RoleTypeComponent {
selected_role:string='';
constructor(private router: Router,private shared:Shared) {}

 onSelection(){
  this.shared.selectedrole=this.selected_role;
  if(this.shared.signin_flag==true){
   if(this.selected_role=='coach') this.router.navigate(['/signin']);
   else if(this.selected_role=='user') this.router.navigate(['/signin']);
   else if(this.selected_role=='admin') this.router.navigate(['/signin']);
  }
  else{
    if(this.selected_role=='coach') this.router.navigate(['/coach-signup']);
    else if(this.selected_role=='user') this.router.navigate(['/signup']);
  }
}
}
