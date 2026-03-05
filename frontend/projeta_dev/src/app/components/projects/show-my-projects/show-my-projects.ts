import { Component } from '@angular/core';
import { NgClass } from '@angular/common';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-show-my-projects',
  imports: [NgClass, RouterLink],
  templateUrl: './show-my-projects.html',
  styleUrl: './show-my-projects.css',
})
export class ShowMyProjects {
  myProjectsOpen = false;
  iamInProjectsOpen = false;

}
