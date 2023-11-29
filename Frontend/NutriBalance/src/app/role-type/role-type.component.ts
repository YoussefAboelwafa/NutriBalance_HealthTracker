import { Component, Injectable } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-role-type',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './role-type.component.html',
  styleUrl: './role-type.component.css'
})
export class RoleTypeComponent {
selected_role:string='';
constructor(private router: Router) {}

 onSelection(){
   if(this.selected_role=='coach') this.router.navigate(['/coach-signup']);
}
}
