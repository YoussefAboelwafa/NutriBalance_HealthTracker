import { Coach } from "./Coach";
import { Plan } from "./Plan";
import { Weight } from "./Weight";

export class User {
  user_id: any;
  username: any;
  email: any;
  password: any;
  contact_number: any;
  comment: any;
  image: any;
  plan: Plan | undefined;
  coach: Coach | undefined;
  coaches_reports: Coach[] | undefined;
  weights: Weight[] | undefined;
  [key: string]: any;

  constructor() {}

  // Static method to create a User instance from a JSON object
  static fromJson(json: any): User {
    const user = new User();
    user.user_id = json.user_id;
    user.username = json.username;
    user.email = json.email;
    user.password = json.password;
    user.contact_number = json.contact_number;
    user.comment = json.comment;
    user.image = json.image;
    user.plan = json.plan;
    user.coach = json.coach;
    user.coaches_reports = json.coaches_reports;
    user.weights = json.weights;
    return user;
  }
}
