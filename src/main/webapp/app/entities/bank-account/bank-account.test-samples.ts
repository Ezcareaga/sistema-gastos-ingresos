import { IBankAccount, NewBankAccount } from './bank-account.model';

export const sampleWithRequiredData: IBankAccount = {
  id: 17167,
  name: 'um via',
  balance: 2293.59,
};

export const sampleWithPartialData: IBankAccount = {
  id: 17763,
  name: 'term pfft',
  balance: 9498.58,
};

export const sampleWithFullData: IBankAccount = {
  id: 13483,
  name: 'gigantic',
  accountNumber: 'fairly fooey ugh',
  balance: 2644.43,
};

export const sampleWithNewData: NewBankAccount = {
  name: 'clonk porter',
  balance: 26302.97,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
