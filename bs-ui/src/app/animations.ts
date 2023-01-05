import {trigger, animate, transition, style} from '@angular/animations';

export const slideInAnimation =

  trigger('slideInAnimation', [
    transition('* <=> *', [
      style({opacity: 0}),
      animate('.3s', style({opacity: 1}))
    ]),
  ]);

// trigger('slideInAnimation', [
//   transition('* <=> *', [
//     style({position: 'relative'}),
//     query(':enter, :leave', [
//       style({
//         position: 'absolute',
//         width: '100%'
//       })
//     ], {optional: true}),
//     query(':enter', [
//       style({opacity: '0'})
//     ], {optional: true}),
//     group([
//       query(':leave', [
//         animate('0ms ease-out', style({opacity: '0'}))
//       ], {optional: true}),
//       query(':enter', [
//         animate('800ms ease-out', style({opacity: '1'}))
//       ], {optional: true})
//     ]),
//   ])
// ]);
