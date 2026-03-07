import { Component, signal } from '@angular/core';
import { Project } from '../../../../types/entity/project';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-show-projects-of-user',
  imports: [RouterLink],
  templateUrl: './show-projects-of-user.html',
  styleUrl: './show-projects-of-user.css',
})
export class ShowProjectsOfUser {
  projectsCount=1;
  projects = signal(<Project[]> ({} as Project[]));
}
