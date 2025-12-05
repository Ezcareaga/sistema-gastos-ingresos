import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'transaction',
    data: { pageTitle: 'Transactions' },
    loadChildren: () => import('./transaction/transaction.routes'),
  },
  {
    path: 'category',
    data: { pageTitle: 'Categories' },
    loadChildren: () => import('./category/category.routes'),
  },
  {
    path: 'bank-account',
    data: { pageTitle: 'BankAccounts' },
    loadChildren: () => import('./bank-account/bank-account.routes'),
  },
  {
    path: 'contact',
    data: { pageTitle: 'Contacts' },
    loadChildren: () => import('./contact/contact.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
