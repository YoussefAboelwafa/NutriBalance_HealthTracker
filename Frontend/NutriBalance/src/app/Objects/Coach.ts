import { Plan } from "./Plan";
import { User } from "./User";

export class Coach{
    coach_id:any;
    username:any;
    password:any;
    email:any;
    contact_number:any;
    description:any;
    cv?: string;
    price:any;
    isapproved:any;
    users:User[]=[];
    plans:Plan[]=[];
}