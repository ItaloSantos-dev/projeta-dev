import { Component } from '@angular/core';
import { NgClass } from '@angular/common';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-show-project',
  imports: [NgClass, RouterLink],
  templateUrl: './show-project.html',
  styleUrl: './show-project.css',
})
export class ShowProject {
  usersOpen: boolean = false;
}
