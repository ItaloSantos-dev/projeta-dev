import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-show-user',
  imports: [RouterLink, NgClass],
  templateUrl: './show-user.html',
  styleUrl: './show-user.css',
})
export class ShowUser {
  projetosOpen = false;
}
