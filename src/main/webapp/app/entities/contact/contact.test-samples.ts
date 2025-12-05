import { IContact, NewContact } from './contact.model';

export const sampleWithRequiredData: IContact = {
  id: 10916,
  name: 'gastropod',
};

export const sampleWithPartialData: IContact = {
  id: 14544,
  name: 'during noon',
  email: 'Elmira.Reynolds@hotmail.com',
};

export const sampleWithFullData: IContact = {
  id: 4920,
  name: 'youthful cutover scowl',
  identification: 'gentle',
  email: 'Kiera_Rice1@hotmail.com',
  phone: '349-748-7016 x4159',
};

export const sampleWithNewData: NewContact = {
  name: 'standard but',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
