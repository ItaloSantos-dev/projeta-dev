import { inject, Injectable } from '@angular/core';
import { BackApi } from '../../api/back-api';
import { Observable } from 'rxjs';
import { User } from '../../../types/entity/user';
import { ProjectRequestNotification } from '../../../types/entity/project-request-notification';
import { UserWithProjects } from '../../../types/entity/user-with-projects';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private backApi = inject(BackApi);

  getUserByUsername(username:string):Observable<User>{
    return this.backApi.getUserByUsername(username);
  }

  getNotificationsOfUserById():Observable<ProjectRequestNotification[]>{
    return this.backApi.getNotificationsOfUserById();
  }

  setNotificationReadById(id:number):Observable<void>{
    return this.backApi.setNotificationReadById(id);
  }

  getFollowingOfUser(username:string):Observable<User[]>{
    return this.backApi.getFollowingOfUser(username);
  }

  getFollowersOfUser(username:string):Observable<User[]>{
    return this.backApi.getFollowersOfUser(username);
  }
  deleteByUserFollowingIdAndUserFollowedId(id:number):Observable<void>{
    return this.backApi.deleteByUserFollowingIdAndUserFollowedId(id);
  }

  followUserById(id:number):Observable<void>{
    return this.backApi.followUserById(id);
  }

  getUserWithProjects(username:string):Observable<UserWithProjects>{
      return this.backApi.getUserWithProjects(username);
  }
}
