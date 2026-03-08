import {Component, inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {UserService} from '../user.service';
import {User} from '../user';
import {Router, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userService: UserService = inject(UserService);
  router: Router = inject(Router);

  username: string = '';
  password: string = '';

  login() {
    const payload = {
      username: this.username,
      password: this.password
    };

    this.userService.login(payload).subscribe(user => {
      console.log(user);
      sessionStorage.setItem("user", JSON.stringify(user));
      sessionStorage.setItem("password", this.password);
      //let u:User = JSON.parse(<string>sessionStorage.getItem("user"));
      this.router.navigate(['/']);
    })
  }

  register() {
    const payload = {
      username: this.username,
      password: this.password
    };

    this.userService.create(payload).subscribe(user => {
      console.log(user);
      sessionStorage.setItem("user", JSON.stringify(user));
      sessionStorage.setItem("password", this.password);
      this.router.navigate(['/']);
    })
  }

}
