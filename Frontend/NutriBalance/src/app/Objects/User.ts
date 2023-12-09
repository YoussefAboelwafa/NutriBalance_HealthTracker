import { Coach } from "./Coach";
import { Plan } from "./Plan";

export class User {
  user_id: any;
  username: any;
  email: any;
  password: any;
  contact_number: any;
  comment: any;
  plan:Plan|undefined;
  coaches:Coach|undefined;
}
