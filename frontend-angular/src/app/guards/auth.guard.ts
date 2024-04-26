import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import {inject, Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";

// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };

@Injectable() // pour ce guard soit un service
export class AuthGuard {
  constructor(private authService: AuthService,
              private router:Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if (this.authService.isAuthenticated) {
      return true;
    } else {
      this.router.navigateByUrl('/login')
      return false;
    }
  }
}

