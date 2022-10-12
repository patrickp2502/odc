export const convertToFromatedDateString = (dateString: string | null): string => {
    return dateString ? new Date(dateString).toUTCString() : "";
}