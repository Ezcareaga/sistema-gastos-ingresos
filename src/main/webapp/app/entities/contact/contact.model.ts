export interface IContact {
  id: number;
  name?: string | null;
  identification?: string | null;
  email?: string | null;
  phone?: string | null;
}

export type NewContact = Omit<IContact, 'id'> & { id: null };
